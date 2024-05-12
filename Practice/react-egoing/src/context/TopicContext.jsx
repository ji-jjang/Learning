import { createContext, useState, useContext } from "react";

export const TopicContext = createContext();

export const TopicProvider = ({ children }) => {
  const [topics, setTopics] = useState([
    { id: 1, title: "HTML", body: "HTML is ..." },
    { id: 2, title: "CSS", body: "CSS is ..." },
    { id: 3, title: "JavaScript", body: "JavaScript is ..." },
  ]);

  const [selectedId, setSelectedId] = useState(null);

  const [mode, setMode] = useState("WELCOME");

  const addTopic = (title, body) => {
    const newTopic = { id: topics.length + 1, title, body };
    setTopics([...topics, newTopic]);
    setMode("READ");
    setSelectedId(newTopic.id);
  };

  const updateTopic = (id, title, body) => {
    setTopics(
      topics.map((topic) =>
        topic.id === id ? { ...topic, title, body } : topic,
      ),
    );
    setMode("READ");
  };

  const deleteTopic = (id) => {
    setTopics(topics.filter((topic) => topic.id !== id));
    setMode("WELCOME");
    setSelectedId(null);
  };

  return (
    <TopicContext.Provider
      value={{
        topics,
        addTopic,
        updateTopic,
        deleteTopic,
        selectedId,
        setSelectedId,
        mode,
        setMode,
      }}
    >
      {children}
    </TopicContext.Provider>
  );
};

export const useTopics = () => useContext(TopicContext);
