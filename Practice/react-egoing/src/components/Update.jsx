import React, { useState, useContext } from "react";
import { TopicContext } from "../context/TopicContext";

function Update({ initialTitle, initialBody }) {
  const [title, setTitle] = useState(initialTitle);
  const [body, setBody] = useState(initialBody);
  const { updateTopic, selectedId } = useContext(TopicContext);

  const handleSubmit = (event) => {
    event.preventDefault();
    updateTopic(selectedId, title, body);
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
        <button type="submit">Update</button>
      </p>
    </form>
  );
}

export default Update;
