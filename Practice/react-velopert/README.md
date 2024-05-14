# React

## 리액트는 어쩌다 만들어졌을까?

- 기존에는 브라우저의 DOM Selector API 를 사용해서 특정 DOM 을 선택한뒤, 특정 이벤트가 발생하면 변화를 주도록 설정한다.
- 사용자와의 인터랙션이 별로 없는 웹페이지라면 상관없겠지만, 만약에 인터랙션이 자주 발생하고, 이에 따라 동적으로 UI 를 표현해야된다면, 이러한 규칙이 정말 다양해질것이고, 그러면 관리하기도 힘들어진다.

> 리액트는 어떠한 상태가 바뀌었을때, 그 상태에 따라 DOM 을 어떻게 업데이트 할 지 규칙을 정하는 것이 아니라, 아예 다 날려버리고 처음부터 모든걸 새로 만들어서 보여준다면 어떨까?

- 하지만, 정말로 동적인 UI 를 보여주기 위해서 모든걸 다 날려버리고 모든걸 새로 만들게 된다면, 속도가 굉장히 느릴 것이다. 따라서 리액트에서는 `Virtual DOM` 이라는 것을 사용
- Virtual DOM 은 가상의 DOM 인데요, 브라우저에 실제로 보여지는 DOM 이 아니라 그냥 메모리에 가상으로 존재하는 DOM 으로서 그냥 JavaScript 객체이기 때문에 작동 성능이 실제로 브라우저에서 DOM 을 보여주는 것 보다 속도가 훨씬 빠르다.
- 리액트는 상태가 업데이트 되면, 업데이트가 필요한 곳의 UI 를 Virtual DOM 을 통해서 렌더링합니다. 그리고 나서 리액트 개발팀이 만든 매우 효율적인 비교 알고리즘을 통하여 실제 브라우저에 보여지고 있는 DOM 과 비교를 한 후, 차이가 있는 곳을 감지하여 이를 실제 DOM 에 패치

## JSX 기본규칙

### 1. 태그는 꼭 닫혀있어야 한다.

```jsx
function App() {
  return (
    <div>
      <input />
      <br />
    </div>
  );
}
```

### 2. 두 개 이상의 태그는 하나의 태그로 감싸져야 한다.

```jsx
function App() {
  return (
    <div>
      <input />
      <br />
    </div>
  );
}
```

- 단순히 감싸기 위해 불필요한 div로 감싸면 스타일 관련 설정에서 복잡해질 수 있다. 리액트의 `Fragment`를 이용한다.
- Fragment 는 브라우저 상에서 따로 별도의 엘리먼트로 나타나지 않는다.

```jsx
function App() {
  return (
    <>
      <Hello />
      <div>Hello</div>
    </>
  );
}
```

### 3. JSX 안에서 자바스크립트 값 사용은 {}을 이용한다.

```jsx
function App() {
  const name = "react";
  return (
    <>
      <div>{name}</div>
    </>
  );
}
```

### 4. 인라인 스타일은 객체 형태로 작성한다.

```jsx
function App() {
  const name = "react";
  const style = {
    backgroundColor: "black", //background-color camelCase 적용
    color: "aqua",
    fontSize: 24, // 기본 단위 px
    padding: "1rem", // 다른 단위 사용 시 문자열로 설정
  };

  return (
    <>
      <Hello />
      <div style={style}>{name}</div>
    </>
  );
}

export default App;
```

### 5. CSS class 설정 시 className=으로 설정한다.

```jsx
return (
  <>
    <Hello />
    <div style={style}>{name}</div>
    <div className="red-box"></div>
  </>
);
```

## props

- 컴포넌트에 값을 전달할 때 사용한다.

```jsx
export default function App() {
  return (
    <>
      <Hello name="juny" color="red"/>
    </>
  );
}

export default function Hello(props) {
  return (
    <div>
      안녕하세요 {props.name}, 저는 {props.color}을 좋아해요.
    </div>
  );
}

// 안녕하세요 juny, 저는 red을 좋아해요.
```

- 비구조화 할당

```jsx
export default function App() {
  return (
    <>
      <Hello name="juny" color="red"/>
    </>
  );
}

export default function Hello({name, color}) {
  return (
    <div>
      안녕하세요 {name}, 저는 {color}을 좋아해요.
    </div>
  );
```

### default prop

- prop을 지정하지 않았으면 아래와 같이 기본 prop을 지정할 수 있다.

```jsx
Hello.defaultProps = {
  name: "이름없음",
};
```

### props.children

- children은 컴포넌트가 받는 props 중 하나로, 컴포넌트 태그 사이에 위치한 JSX 컨텐츠를 참조한다.

```jsx
import React from "react";

function Wrapper({ children }) {
  const style = {
    border: "2px solid black",
    padding: "16px",
  };
  return <div style={style}>{children}</div>;
}

export default Wrapper;
```

```jsx
export default function App() {
  return (
    <Wrapper>
      <Hello name="react" color="red" isSpecial={true} />
      <Hello color="pink" />
    </Wrapper>
  );
}
```

- chilrdren 키워드를 다른 것으로 쓸 수 있을까? 다른 키워드를 쓸 수 있다. App의 Wrapper 컴포넌트 내부에서도 지정해야 한다. 하지만 이는 일반적인 react 패턴이 아니라 가독성이 저하된다.

## useState

### 콜백 함수

- 어떤 함수에 인자로 전달되는 함수를 말한다.
- 예시

#### 1. 버튼을 클릭할 때 호출되는 콜백 함수

```jsx
onClick = { onIncrease };
```

- onClick이라는 이벤트가 발생할 때 호출되어야 하는 함수를 onIncrease로 지정하므로 콜백 함수로 볼 수 있다.

#### 2. 함수형 상태 업데이트 콜백 함수

```jsx
setNumber((prevNumber) => prevNumber + 1);
```

- setNumber 함수는 React의 useState 훅에서 제공되며, 상태를 업데이트하는 함수
- 콜백 함수는 현재 상태를 기반으로 다음 상태를 계산하며, 함수형 업데이트라고 부른다.
- setNumber(prev => prev + 1) vs setNumber(number + 1)
  - 전자는 함수를 인자로 받아 현재 상태를 기반으로 다음 상태를 계산한다. 따라서 상태 의존성이 제거되고, 여러 상태 업데이트가 동시에 발생할 때 각 업데이트는 독립적으로 처리된다.
  - 후자는 현태 상태 값을 직접 참조하여 새로운 상태 값을 계산한다. 경쟁 조건이 발생할 수 있다.

#### 3. 콜백 지옥

```jsx
function increase(number, callback) {
  setTimeout(() => {
    const result = number + 10;
    if (callback) {
      callback(result);
    }
  }, 1000);
}

// 하나의 콜백 함수
increase(0, (result) => {
  console.log(result);
});

// 콜백 지옥
console.log("작업 시작");
increase(0, (result) => {
  console.log(result);
  console.log("첫번째 작업 완료");
  increase(result, (result) => {
    console.log(result);
    console.log("두번째 작업 완료");
    increase(result, (result) => {
      console.log(result);
      console.log("세번째 작업 완료");
    });
  });
});
```

### 사용법

```jsx
const numberState = useState(0);
const number = numberState[0];
const setNumber = numberState[1];
```

- useState의 첫번째 원소는 현재 상태, 두번째 원소는 Setter함수가 있다. 이 두개로 상태를 관리한다.
- 배열 비구조화 할당을 이용하면 아래와 같이 짧게 선언할 수 있다.

```jsx
const [number, setNumber] = useState(0);
```

### onChange

- onChange 이벤트 핸들러는 사용자가 입력 필드에 타이핑을 할 때마다 입력 값을 상태에 반영하기 위해 사용
- 이벤트 함수에서는 이벤트 객체를 파라미터로 받아와서 이벤트가 발생한 DOM의 정보를 가져올 수 있다. 즉, 아래와 같이 input 값을 가져올 수 있다.

```jsx
function InputSample() {
  const [text, setText] = useState('');

  const onChange = (e) => {
    setText(e.target.value);
  };

 <input onChange={onChange} value={text}  />
```

### 여러 개의 input 상태 관리

1. 객체를 저장하는 useState

```jsx
const [inputs, setInputs] = useState({
  name: "",
  nickname: "",
});
```

2. 비구조화 할당을 통해 name, nickname 값 추출

```jsx
const { name, nickname } = inputs;
```

3. input 값 변화에 따라 상태 관리(onChange)

```jsx
return (
  <div>
    <input name="name" placeholder="이름" onChange={onChange} value={name} />
    <input
      name="nickname"
      placeholder="닉네임"
      onChange={onChange}
      value={nickname}
    />
    <button onClick={onReset}>초기화</button>
    <div>
      <b>값: </b>
      {name} {nickname}
    </div>
  </div>
);
```

4. onChange 콜백 함수

```jsx
const onChange = (e) => {
  const { value, name } = e.target;
  // e.target에 들어있는 값은 아래와 같다. (na와 ni 입력한 상태)
  // <input name="name" placeholder="이름" value="na..."
  // <input name="nickname" placeholder="넥네임" value="ni..."

  setInputs({
    ...inputs,
    [name]: value,
  });
};
```

- 여기에서 중요한 점은 기존의 input 객체를 복사하기 위해 `...inputs` 스프레드 연산자를 사용했다는 점이다.
- `inputs[name] = value` 기존 상태를 직접 수정하게 되면 값을 바꿔도 렌더링이 되지 않는다. 왜 일까?

```jsx
shouldComponentUpdate(nextProps: Props, nextState: State) {
  for (const [key, value] of Object.entries(nextState)) {
    if (typeof value != "object") {
      if (this.state.isLoading != nextState.isLoading) {
        return true;
      }
    }
    else {
      // codes for deep comparison here depending on your case
    }
  }
  return false;
}
```

- setState는 위의 함수를 트리거하며 이 메소드의 반환값에 따라 render 호출 여부가 결정된다. 단순히 값을 변경한다면 shouldComponentUpdate에서 현재 incremental값이 변경될 값과 동일하므로 변경점을 찾지 못할 것이고 결국 false를 반환하게 된다. 상태 객체의 참조가 변하지 않기 때문에 React는 상태가 변경되었다는 것을 감지하지 못하고, 따라서 컴포넌트를 재렌더링하지 않는 것이다.
- 스프레드 연산자를 사용하는 이유는 상태 객체의 불변성을 유지하면서 새로운 객체를 생성하기 위함이다. 기존 상태 객체를 새로운 객체로 복사하고, 추가적인 변경사항을 이 새 객체에 적용하기 위에 아래와 같은 형태를 사용하는 것이다.

```jsx
setInputs({
  ...inputs,
  [name]: value,
});
```

## useRef

### 1. DOM 선택

- 특정 이벤트가 발생했을 때 포커싱 변경하기

```jsx
const nameInput = useRef();

const onReset = () => {
  setInputs({
    name: "",
    nickname: "",
  });
  nameInput.current.focus(); // onReset 이벤트에 포커싱 설정
};

<input
  name="name"
  placeholder="이름"
  onChange={onChange}
  value={name}
  ref={nameInput}
/>; // name input란에 추가
```

### 2. 컴포넌트 안에서 조회 및 수정 할 수 있는 변수를 관리

- heap 영역에 저장되는 객체로 프로그램이 시작하면 늘 같은 주소를 가지고 있다.
- useRef로 관리되는 변수는 값이 바뀌어도 컴포넌트가 리렌더링 되지 않는다.
- useRef로 관리하는 변수
  - setTimeout, setInterval 을 통해서 만들어진 id
  - 외부 라이브러리를 사용하여 생성된 인스턴스
  - scroll 위치

```jsx
const nextId = useRef(4);
const onCreate = () => {
  // 사용자를 추가하는 로직
  setInputs({
    username: "",
    email: "",
  });
  nextId.current += 1;
};
```

- 4번 유저부터 생성되도록 useRef를 이용한다.

### map을 통한 순회

```jsx
<div>
  {users.map((user) => (
    <User user={user} key={user.id} />
  ))}
</div>
```

- key가 없다면 랜더링 시 굉장히 비효율적으로 동작한다. 앞의 원소가 삭제되었을 경우 모든 원소가 리랜더링 된다.

## 간단한 CRUD

### 1. CREATE

```jsx
const nextId = useRef(4);
const onCreate = () => {
  const user = {
    id: nextId.current,
    username,
    email,
  };
  // 무결성이 깨져도 호출이 되는지
  setUsers([...users, user]);

  setInputs({
    username: "",
    email: "",
  });
  nextId.current += 1;
};
```

- spread 연산자를 이용했지만 concat 연산자를 이용할수도 있다.

### 3. UPDATE

```jsx
const onToggle = (id) => {
  setUsers(
    users.map((user) =>
      user.id === id ? { ...user, active: !user.active } : user,
    ),
  );
};
```

- user 객체를 먼저 복사하고 나서 active 상태를 반전시키는 토클이다.

### 4. DELETE

```jsx
const onRemove = (id) => {
  setUsers(users.filter((user) => user.id !== id));
};
```

- filter 연산자는 배열 내 각 요소에 대해 주어진 함수를 실행하고, 그 함수가 true를 반환하는 모든 요소로 새 배열을 생성한다.
- 아이디가 일치하지 않는 경우 true, 즉 일치하는 id만 제외해서 새로운 유저 그룹을 만드는 것이다.

## useEffect

- 컴포넌트가 마운트 되거나 언마운트 또는 변경되었을 때 실행되는 Hook이다.
- 리액트 컴포넌트는 기본적으로 부모컴포넌트가 리랜더링되면 자식 컴포넌트는 바뀐 내용이 없더라도 리랜더링 된다.
- `class형 컴포넌트 생명주기`과 `함수형 컴포넌트 생명주기`가 존재하며 함수형 컴포넌트 생명주기에 대해서 알압론다.

### 함수형 컴포넌트 생명주기

- Mounting constructor() 함수형 컴포넌트 내부
- Mounting render() return()
- Mounting ComponenDidMount() useEffect()
- Updating componentDidUpdate() useEffect()
- UnMounting componentWillUnmount() useEffect()

### 1. deps 비우기

```jsx
useeffect(() => {
  console.log("컴포넌트가 화면에 나타남");
  return () => {
    console.log("컴포넌트가 화면에서 사라짐");
  };
}, []);
```

- 만약에 deps 배열을 비우게 된다면, 컴포넌트가 처음 나타날때에만 useEffect 에 등록한 함수가 호출된다.
- 마운트 시에 하는 작업들은 다음과 같은 사항
  - props 로 받은 값을 컴포넌트의 로컬 상태로 설정
  - 외부 API 요청 (REST API 등)
  - 라이브러리 사용 (D3, Video.js 등...)
  - setInterval 을 통한 반복작업 혹은 setTimeout 을 통한 작업 예약
- 컴포넌트가 화면에서 사라질 때 호출된다.
- useEffect 에서는 함수를 반환 할 수 있는데 이를 cleanup 함수라고 한다.
- deps 가 비어있는 경우에는 컴포넌트가 사라질 때 cleanup 함수가 호출된다.

### 2. deps에 특정 값 넣기

```jsx
useEffect(() => {
  console.log("user 값이 설정됨");
  console.log(user);
  return () => {
    console.log("user 가 바뀌기 전..");
    console.log(user);
  };
}, [user]);
```

- useEffect 안에서 사용하는 상태나, props 가 있다면, useEffect 의 deps 에 넣어야 한다. (규칙)

### 3. deps 생략

```jsx
useEffect(() => {
  console.log(user);
});
```

- 컴포넌트가 리렌더링 될 때마다 호출된다.

## useMemo

- 첫번째 파라미터에는 어떻게 연산할지 정의하는 함수를 넣어주고, 두번째 파라미터에는 deps 배열을 넣어준다.
- deps 배열 안에 넣은 내용이 바뀌면, 등록한 함수를 호출해서 값을 연산하고 만약에 내용이 바뀌지 않았다면 이전에 연산한 값을 재사용하게 된다.

```jsx
function countActiveUsers(users) {
  console.log("활성 사용자 수를 세는중...");
  return users.filter((user) => user.active).length;
}

const count = useMemo(() => countActiveUsers(users), [users]);
```

- 왜 사용하는가? 사용하지 않으면 input태그 내용이 바뀌더라도 countActiveUsers가 호출되어 성능에 악영향을 미친다. (값이 변경되지 않았더라면 이전에 연산한 값을 재사용할 수 있게 useMemo를 사용하자!)

## useCallback

- useMemo는 특정 결과값을 재사용할 때 사용하는 반면, userCallback은 특정 함수를 새로 만들지 않고 재사용하고 싶을 때 사용한다.
- 함수를 선언하는 것 자체는 사실 메모리도, CPU 도 리소스를 많이 차지 하는 작업은 아니기 때문에 함수를 새로 선언한다고 해서 그 자체 만으로 큰 부하가 생길일은 없지만, 한번 만든 함수를 필요할때만 새로 만들고 재사용하는 것은 여전히 중요하다.
- 나중에 컴포넌트에서 props 가 바뀌지 않았으면 Virtual DOM 에 새로 렌더링하는 것 조차 하지 않고 컴포넌트의 결과물을 재사용 하는 최적화 작업을 할 때 함수를 재사용하는 것이 필수적이다.
- 주의 할 점은 함수 안에서 사용하는 상태 혹은 props 가 있다면 꼭, deps 배열안에 포함시켜야 된다는 것

## useCallback과 함수형 업데이트

```jsx
const onChange = (e) => {
  const { name, value } = e.target;
  setInputs({
    ...inputs,
    [name]: value,
  });
};

// useCallback
const onChange = useCallback(
  (e) => {
    const { name, value } = e.target;
    setInputs({
      ...inputs,
      [name]: value,
    });
  },
  [inputs],
);

// 함수형 업데이트
const onChange = useCallback((e) => {
  const { name, value } = e.target;
  setInputs((inputs) => ({
    ...inputs,
    [name]: value,
  }));
}, []);
```

```jsx
const onCreate = () => {
    const user = {
      id: nextId.current,
      username,
      email,
    };
    setUsers(users.concat(user));

    setInputs({
      username: "",
      email: "",
    });
    nextId.current += 1;

const onCreate = useCallback(() => {
    const user = {
      id: nextId.current,
      username,
      email,
    };
    setUsers(users.concat(user));

    setInputs({
      username: "",
      email: "",
    });
    nextId.current += 1;
  }, [users, username, email]);

const onCreate = useCallback(() => {
  const user = {
    id: nextId.current,
    username,
    email,
  };
  setUsers(users => users.concat(user));

  setInputs({
    username: '',
    email: ''
  });
  nextId.current += 1;
  }, [username, email]);
```

```jsx
const onRemove = (id) => {
  setUsers(users.filter((user) => user.id !== id));
};

const onRemove = useCallback(
  (id) => {
    setUsers(users.filter((user) => user.id !== id));
  },
  [users],
);

const onRemove = useCallback((id) => {
  setUsers((users) => users.filter((user) => user.id !== id));
}, []);
```

```jsx
const onToggle = (id) => {
  setUsers(
    users.map((user) =>
      user.id === id ? { ...user, active: !user.active } : user,
    ),
  );
};

const onToggle = useCallback(
  (id) => {
    setUsers(
      users.map((user) =>
        user.id === id ? { ...user, active: !user.active } : user,
      ),
    );
  },
  [users],
);
const onToggle = useCallback((id) => {
  setUsers((users) =>
    users.map((user) =>
      user.id === id ? { ...user, active: !user.active } : user,
    ),
  );
}, []);
```

- useCallback을 보면 deps에 users가 들어 있어서 배열이 바뀔때마다 함수가 새로 만들어진다. 즉, 어떤 유저를 수정하더라도 전체 유저에 대해 랜더링이 다시 되는 것이다. 이 문제를 해결할 수 있을까? 바로 함수형 업데이트를 사용한다. 함수형 업데이트는 콜백 함수 파라미터에서 최신 users를 참조할 수 있기에 deps에 users를 넣지 않아도 된다.
- useCallback, useMemo, React.memo 는 컴포넌트의 성능을 실제로 개선할수있는 상황에서만 해야 한다. 예를 들어 User 컴포넌트에 b 와 button 에 onClick 으로 설정해준 함수들은, 해당 함수들을 useCallback 으로 재사용한다고 해서 리렌더링을 막을 수 있는것은 아니다. 또한,렌더링 최적화 하지 않을 컴포넌트에 React.memo 를 사용하는것은 불필요한 props 비교만 하는 것이기 때문에 오히려 성능에 악영향을 줄 수 있다.

### react devtools

- 어떤 컴포넌트가 렌더링되고 있는지 확인
- Highlight updates when components render. 체크

### react.memo를 이용한 컴포넌트 리랜더링 방지

- 컴포넌트의 props가 바뀌지 않았다면 랜더링 되지 않도록 한다.
- createUser를 감싸준다.
- React.memo 두번째 파라미터에 propsAreEqual 함수를 사용하여 특정 값 비교도 가능하다.

```jsx
import React from "react";

const CreateUser = ({ username, email, onChange, onCreate }) => {
  return (
    <div>
      <input
        name="username"
        placeholder="계정명"
        onChange={onChange}
        value={username}
      />
      <input
        name="email"
        placeholder="이메일"
        onChange={onChange}
        value={email}
      />
      <button onClick={onCreate}>등록</button>
    </div>
  );
};

export default React.memo(CreateUser);
```

- UserList 컴포넌트도 감싸준다.

```jsx
import React from "react";

const User = React.memo(function User({ user, onRemove, onToggle }) {
  return (
    <div>
      <b
        style={{
          cursor: "pointer",
          color: user.active ? "green" : "black",
        }}
        onClick={() => onToggle(user.id)}
      >
        {user.username}
      </b>
      &nbsp;
      <span>({user.email})</span>
      <button onClick={() => onRemove(user.id)}>삭제</button>
    </div>
  );
});

function UserList({ users, onRemove, onToggle }) {
  return (
    <div>
      {users.map((user) => (
        <User
          user={user}
          key={user.id}
          onRemove={onRemove}
          onToggle={onToggle}
        />
      ))}
    </div>
  );
}

export default React.memo(UserList);
```

### userReducer

- 상태를 관리할 때 useState 말고 또 다른 방법.
- Hook 함수를 사용하면 컴포넌트의 상태 업데이트 로직을 컴포넌트에서 분리시킬 수 있다. 상태 업데이트 로직을 컴포넌트 바깥에 작성 할 수 있고, 심지어 다른 파일에 작성 후 불러와서 사용 할 수도 있다.
```jsx
function reducer(state, action) {
  // 새로운 상태를 만드는 로직
  // const nextState = ...
  return nextState;
}
```
```jsx
const [state, dispatch] = useReducer(reducer, initialState);
```
- state는 앞으로 컴포넌트에서 사용할 수 있는 상태를 가리킨다. dispatch는 액션을 발생시키는 함수이다. `dispatch({ type: 'INCREMENT' })`처럼 사용한다.
- 구조가 아래처럼 확장된다.
```jsx
function reducer(state, action) {
  switch (action.type) {
    case 'CHANGE_INPUT':
      return {
        ...state,
        inputs: {
          ...state.inputs,
          [action.name]: action.value
        }
      };
    case 'CREATE_USER':
      return {
        inputs: initialState.inputs,
        users: state.users.concat(action.user)
      };
    case 'TOGGLE_USER':
      return {
        ...state,
        users: state.users.map(user =>
          user.id === action.id ? { ...user, active: !user.active } : user
        )
      };
    case 'REMOVE_USER':
      return {
        ...state,
        users: state.users.filter(user => user.id !== action.id)
      };
    default:
      return state;
  }
}

function App() {
  const [state, dispatch] = useReducer(reducer, initialState);
  const nextId = useRef(4);

  const { users } = state;
  const { username, email } = state.inputs;

  const onChange = useCallback(e => {
    const { name, value } = e.target;
    dispatch({
      type: 'CHANGE_INPUT',
      name,
      value
    });
  }, []);

  const onCreate = useCallback(() => {
    dispatch({
      type: 'CREATE_USER',
      user: {
        id: nextId.current,
        username,
        email
      }
    });
    nextId.current += 1;
  }, [username, email]);

  const onToggle = useCallback(id => {
    dispatch({
      type: 'TOGGLE_USER',
      id
    });
  }, []);

  const onRemove = useCallback(id => {
    dispatch({
      type: 'REMOVE_USER',
      id
    });
  }, []);

  return (
    <>
      <CreateUser
        username={username}
        email={email}
        onChange={onChange}
        onCreate={onCreate}
      />
      <UserList users={users} onToggle={onToggle} onRemove={onRemove} />
      <div>활성사용자 수 : 0</div>
    </>
  );
}
```