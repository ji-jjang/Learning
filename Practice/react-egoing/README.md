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
