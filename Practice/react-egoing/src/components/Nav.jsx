import React, { useContext } from "react";
import { TopicContext } from "../context/TopicContext";

function Nav() {
  const { topics, setSelectedId, setMode } = useContext(TopicContext);

  return (
    <nav>
      <ul>
        {topics.map((topic) => (
          <li
            key={topic.id}
            onClick={() => {
              setSelectedId(topic.id);
              setMode("READ");
            }}
          >
            {topic.title}
          </li>
        ))}
      </ul>
    </nav>
  );
}

export default Nav;
