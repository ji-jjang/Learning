import React, { useContext } from "react";
import { TopicContext } from "../context/TopicContext";
import Article from "./Article";
import Create from "./Create";
import Update from "./Update";
import Header from "./Header";
import Nav from "./Nav";

export default function ContentArea() {
  const { mode, setMode, selectedId, topics, deleteTopic } =
    useContext(TopicContext);

  let content = null;
  let contextControl = null;

  const handleWelcomeClick = (event) => {
    event.preventDefault();
    setMode("WELCOME");
  };

  function handleCreateTopic() {
    setMode("CREATE");
  }

  const handleCreate = (title, body) => {
    const newTopic = { id: nextId, title, body };
    setTopics([...topics, newTopic]);
    setId(nextId);
    setNextId(nextId + 1);
    setMode("READ");
  };

  if (mode === "WELCOME") {
    content = <Article title="Hi" body="Hello, Web" />;
  } else if (mode === "READ") {
    const topic = topics.find((topic) => topic.id === selectedId);
    content = topic ? (
      <Article title={topic.title} body={topic.body} />
    ) : (
      <p>Topic not found</p>
    );
    contextControl = (
      <>
        <p>
          <button onClick={() => setMode("UPDATE")}>Update</button>
        </p>
        <p>
          <button onClick={() => deleteTopic(selectedId)}>Delete</button>
        </p>
      </>
    );
  } else if (mode === "CREATE") {
    content = <Create onCreate={handleCreate} />;
  } else if (mode === "UPDATE") {
    const topic = topics.find((topic) => topic.id === selectedId);
    content = <Update initialTitle={topic.title} initialBody={topic.body} />;
  }

  return (
    <>
      <Header title="Web" onWelcome={handleWelcomeClick} />
      <Nav />
      <div>
        {content}
        {mode === "WELCOME" && (
          <button onClick={handleCreateTopic}>Create New Topic</button>
        )}
        {contextControl}
      </div>
    </>
  );
}
