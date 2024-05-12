import React, { useState, useContext } from "react";
import { TopicContext } from "../context/TopicContext";

export default function Create() {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const { addTopic } = useContext(TopicContext);

  const handleSubmit = (event) => {
    event.preventDefault();
    addTopic(title, body);
  };

  return (
    <form onSubmit={handleSubmit}>
      <p>
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Title"
        />
      </p>
      <p>
        <textarea
          value={body}
          onChange={(e) => setBody(e.target.value)}
          placeholder="Body"
        />
      </p>
      <p>
        <button type="submit">Create</button>
      </p>
    </form>
  );
}
