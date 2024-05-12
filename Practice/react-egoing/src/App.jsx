import { useState } from "react";

function Header({ title, onWelcome }) {
  return (
    <header>
      <h1>
        <a href="/" onClick={onWelcome}>
          {title}
        </a>
      </h1>
    </header>
  );
}

function Nav({ topics, onSelectTopic }) {
  return (
    <nav>
      <ol>
        {topics.map((topic) => (
          <li key={topic.id}>
            <a
              href={`/read/${topic.id}`}
              onClick={(event) => onSelectTopic(event, topic.id)}
            >
              {topic.title}
            </a>
          </li>
        ))}
      </ol>
    </nav>
  );
}

function Article(props) {
  return (
    <article>
      <h2>{props.title}</h2>
      {props.body}
    </article>
  );
}

function Create(props) {
  return (
    <article>
      <h2>Create</h2>
      <form
        onSubmit={(event) => {
          event.preventDefault();
          const title = event.target.title.value;
          const body = event.target.body.value;
          props.onCreate(title, body);
        }}
      >
        <p>
          <input type="text" name="title" placeholder="title" />
        </p>
        <p>
          <textarea name="body" placeholder="body"></textarea>
        </p>
        <p>
          <input type="submit" value="Create"></input>
        </p>
      </form>
    </article>
  );
}

export default function App() {
  const [mode, setMode] = useState("WELCOME");
  const [id, setId] = useState(null);
  const [nextId, setNextId] = useState(4);

  const [topics, setTopics] = useState([
    { id: 1, title: "html", body: "html is ..." },
    { id: 2, title: "css", body: "css is ..." },
    { id: 3, title: "javascript", body: "javascript is ..." },
  ]);

  const handleWelcomeClick = (event) => {
    event.preventDefault();
    setMode("WELCOME");
  };

  const handleSelectTopic = (event, topicId) => {
    event.preventDefault();
    setMode("READ");
    setId(topicId);
  };

  const findTopicById = (topicId) => {
    return topics.find((topic) => topic.id === topicId);
  };

  let content = null;
  if (mode === "WELCOME") {
    content = <Article title="Hi" body="Hello, Web" />;
  } else if (mode === "READ") {
    const topic = findTopicById(id);
    if (topic) {
      content = <Article title={topic.title} body={topic.body} />;
    }
  } else if (mode === "CREATE") {
    content = (
      <Create
        onCreate={(_title, _body) => {
          const newTopic = { id: nextId, title: _title, body: _body };
          const newTopics = [...topics];
          newTopics.push(newTopic);
          setTopics(newTopics);
          setMode("READ");
          setId(nextId);
          setNextId(nextId + 1);
        }}
      ></Create>
    );
  }
  return (
    <div>
      <Header title="Web" onWelcome={handleWelcomeClick} />
      <Nav topics={topics} onSelectTopic={handleSelectTopic} />
      {content}
      <a
        href="/create"
        onClick={(event) => {
          event.preventDefault();
          setMode("CREATE");
        }}
      >
        Create
      </a>
    </div>
  );
}
