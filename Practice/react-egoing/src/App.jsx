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

function TopicLink({ topic, onSelectTopic }) {
  return (
    <li>
      <a
        href={`/read/${topic.id}`}
        onClick={(event) => onSelectTopic(event, topic.id)}
      >
        {topic.title}
      </a>
    </li>
  );
}

function Nav({ topics, onSelectTopic }) {
  return (
    <nav>
      <ol>
        {topics.map((topic) => (
          <TopicLink
            key={topic.id}
            topic={topic}
            onSelectTopic={onSelectTopic}
          />
        ))}
      </ol>
    </nav>
  );
}

function Article({ title, body }) {
  return (
    <article>
      <h2>{title}</h2>
      {body}
    </article>
  );
}

function Create({ onCreate }) {
  const handleSubmit = (event) => {
    event.preventDefault();
    const title = event.target.title.value;
    const body = event.target.body.value;
    onCreate(title, body);
  };

  return (
    <article>
      <h2>Create</h2>
      <form onSubmit={handleSubmit}>
        <p>
          <input type="text" name="title" placeholder="title" />
        </p>
        <p>
          <textarea name="body" placeholder="body"></textarea>
        </p>
        <p>
          <input type="submit" value="Send"></input>
        </p>
      </form>
    </article>
  );
}

function Update({ _title, _body, onUpdate }) {
  const [title, setTitle] = useState(_title);
  const [body, setBody] = useState(_body);

  const handleTitleChange = (event) => setTitle(event.target.value);
  const handleBodyChange = (event) => setBody(event.target.value);

  const handleSubmit = (event) => {
    event.preventDefault();
    const title = event.target.title.value;
    const body = event.target.body.value;
    onUpdate(title, body);
  };

  return (
    <article>
      <h2>Update</h2>
      <form onSubmit={handleSubmit}>
        <p>
          <input
            type="text"
            name="title"
            placeholder="title"
            value={title}
            onChange={handleTitleChange}
          />
        </p>
        <p>
          <textarea
            name="body"
            placeholder="body"
            value={body}
            onChange={handleBodyChange}
          ></textarea>
        </p>
        <p>
          <input type="submit" value="Send"></input>
        </p>
      </form>
    </article>
  );
}

export default function App() {
  const [mode, setMode] = useState("WELCOME");
  const [id, setId] = useState(null);
  const [topics, setTopics] = useState([
    { id: 1, title: "html", body: "html is ..." },
    { id: 2, title: "css", body: "css is ..." },
    { id: 3, title: "javascript", body: "javascript is ..." },
  ]);
  const [nextId, setNextId] = useState(4);

  const handleWelcomeClick = (event) => {
    event.preventDefault();
    setMode("WELCOME");
  };

  const handleSelectTopic = (event, topicId) => {
    event.preventDefault();
    setMode("READ");
    setId(topicId);
  };

  const handleCreate = (title, body) => {
    const newTopic = { id: nextId, title, body };
    setTopics([...topics, newTopic]);
    setId(nextId);
    setNextId(nextId + 1);
    setMode("READ");
  };

  const handleUpdate = (title, body) => {
    const newTopics = [...topics];
    const updatedTopic = { id: id, title, body };
    console.log("newTopics: ", newTopics);
    console.log("updatedTopics: ", updatedTopic);
    for (let i = 0; i < newTopics.length; i++) {
      if (newTopics[i].id === id) {
        newTopics[i] = updatedTopic;
        break;
      }
    }
    setTopics(newTopics);
    setMode("READ");
  };

  function handleCreateTopic() {
    setMode("CREATE");
  }

  const findTopicById = (topicId) => {
    return topics.find((topic) => topic.id === topicId);
  };

  let content = null;
  let contextControl = null;
  if (mode === "WELCOME") {
    content = <Article title="Hi" body="Hello, Web" />;
  } else if (mode === "READ") {
    const topic = findTopicById(id);
    if (topic) {
      content = <Article title={topic.title} body={topic.body} />;
    }
    contextControl = (
      <>
        <p>
          <button
            onClick={(event) => {
              event.preventDefault();
              setMode("UPDATE");
            }}
          >
            Update
          </button>
        </p>
        <p>
          <button
            onClick={() => {
              const newTopics = [];
              for (let i = 0; i < topics.length; ++i) {
                if (topics[i].id !== id) {
                  newTopics.push(topics[i]);
                }
              }
              setTopics(newTopics);
            }}
          >
            Delete
          </button>
        </p>
      </>
    );
  } else if (mode === "CREATE") {
    content = <Create onCreate={handleCreate} />;
  } else if (mode === "UPDATE") {
    const topic = findTopicById(id);
    content = (
      <Update _title={topic.title} _body={topic.body} onUpdate={handleUpdate} />
    );
  }
  return (
    <div>
      <Header title="Web" onWelcome={handleWelcomeClick} />
      <Nav topics={topics} onSelectTopic={handleSelectTopic} />
      {content}
      {mode !== "CREATE" && mode !== "UPDATE" && (
        <p>
          <button onClick={handleCreateTopic}>Create</button>
        </p>
      )}
      {contextControl}
    </div>
  );
}
