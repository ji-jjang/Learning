# React

- 리액트는 사용자 정의 태그를 만드는 기술이다.

### prop

```jsx
<a
    href="/"
    onClick={function (event) {
    event.preventDefault();
    props.triggerHeader();
    }}

    <Header
    title="Web"
    triggerHeader={function () {
        alert("Header");
    }}
/>
```

- triggerHeader는 특정 동작을 트리거하기 위한 함수이다.
- 동작
  - triggerHeader를 먼저 prop으로 전달한다.
  - onClick 이벤트 핸들러에서 triggerHandler 함수를 사용한다.
    - 이때, a href의 기본 동작을 무시한다.
    - prop을 통해 제공된 triggerHandler 함수를 실행한다.
- Arrow function으로 변경

```jsx
onClick={(event) => {
event.preventDefault();
props.triggerHeader();
}}

<Header
title="Web"
triggerHeader={() => {
    alert("Header");
}}
/>
```

### state

- 리액트는 변경이 생기더라도 컴포넌트를 다시 랜더링하지 않음. useState를 통해 변경하면 다시 랜더링함.

```jsx
// const _mode = useState("WELCOME");
// const mode = _mode[0];
// const setMode = _mode[1];

const [mode, setMode] = useState("WELCOME");
```

### string, number, bigint, boolean, undefind, symbol, null

```jsx
newValue = {...value}
newValue 변경
setValue(newValue)
```

### object, array

```jsx
newValue = [...value]
newValue 변경
setValue(newValue)
```

### 리액트 코드 호출 흐름

### 1. 사용자 메인화면에서 create 버튼 클릭
- Create 컴포넌트가 호출된다. Create 컴포넌트는 onCreate라는 함수를 prop으로 받는다. prop으로 전달된 handlecreate함수는 title과 body를 가지고 새로운 토픽을 만든다.
```jsx
content = <Create onCreate={handleCreate} />;
```

```jsx
  const handleCreate = (title, body) => {
    const newTopic = { id: nextId, title, body };
    setTopics([...topics, newTopic]);
    setId(nextId);
    setNextId(nextId + 1);
    setMode("READ");
  };
```
### 2. Create 컴포넌트 랜더링
- Create 컴포넌트 내부에 handleSubmit 함수가 있다. 폼 제출 이벤트를 처리한다. 먼저 폼 데이터를 읽어서 onCreate 함수에 전달한다.
```jsx
  const handleSubmit = (event) => {
    event.preventDefault();
    const title = event.target.title.value;
    const body = event.target.body.value;
    onCreate(title, body);
  };

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
```

### 3. Create 컴포넌트 내부에서 폼 데이터 제출
- Create 컴포넌트에서 폼 데이터를 입력하여 Send버튼을 누른다. Send버튼은 handleSubmit을 호출한다. Send 버튼을 누르는 이벤트가 발생하면, title과 body를 폼 입력데이터에서 가져와서 onCreate 프롭을 다시 호출한다.

### createContext, useContext
- TopicContext에서 전역 상태 관리하기
```jsx
export const TopicContext = createContext();

export const TopicProvider = ({ children }) => { 
  // 여러 상태 정의
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

  export const useTopics = () => useContext(TopicContext);
}
```
- 컴포넌트에서 필요한 상태만 끌어서 쓰기
```jsx
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
```

### 전역 상태를 사용하면서 로컬 상태를 사용하는 이유
- 1. 캡슐화 및 재사용성
  - 중요로직은 재사용하면서 변해야 하는 부분을 로컬 상태로 관리하여 재사용성을 높인다.
- 2. 관심사 분리
  - TopicContext주제 목록과 주제 추가, 업데이트, 삭제 등 관련 작업을 주로 관리하고, 업데이트 내 세부 사항(데이터)에 관한 부분을 분리한다.
- 3. 성능 최적화
  - 상태가 구성 요소에서 로컬로 관리되면 동일한 컨텍스트를 사용하는 다른 구성 요소에서 다시 렌더링되는 횟수가 최소화
- 4. 단순성 및 유지 관리성
  - 구성 요소 에서 직접적 title으로 임시 및 양식별 상태를 관리하면 컨텍스트가 더 단순해지고 전역 상태 관리에 더욱 집중하기 용이

### JavaScript map 
- 배열에 있는 각 요소에 대해 주어진 함수를 호출하고, 그 결과로 새로운 배열을 생성
```jsx
{topics.map((topic) => (
          <li
            key={topic.id}
            onClick={() => {
              setSelectedId(topic.id);
              setMode("READ");
            }}
          >
            {topic.id}. {topic.title}
          </li>
        ))}
```

### 이벤트 기본 행위 제어
```jsx
event.preventDefault();
```
- 기본 동작 안하도록 함.