# React + Vite

## axios

### 사용법
- 파라미터에는 API 의 주소가 들어간다.
```jsx
import axios from 'axios';

axios.get('/users/1');
```
- axios.post() 로 데이터를 등록 할 때에는 두번째 파라미터에 등록하고자 하는 정보를 넣는다.
```jsx
axios.post('/users', {
  username: 'blabla',
  name: 'blabla'
});
```
- useState 를 사용하여 요청 상태를 관리하고, useEffect 를 사용하여 컴포넌트가 렌더링되는 시점에 요청을 보낼 수 있다.
- 요청에 대한 상태를 관리 할 때에는 다음과 같이 총 3가지 상태를 관리해야한다.
    1. 요청의 결과
    2. 로딩 상태
    3. 에러
```jsx
function Users() {
  const [users, setUsers] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        // 요청이 시작 할 때에는 error 와 users 를 초기화하고
        setError(null);
        setUsers(null);
        // loading 상태를 true 로 바꿉니다.
        setLoading(true);
        const response = await axios.get(
          'https://jsonplaceholder.typicode.com/users'
        );
        setUsers(response.data); // 데이터는 response.data 안에 들어있습니다.
      } catch (e) {
        setError(e);
      }
      setLoading(false);
    };

    fetchUsers();
  }, []);

  if (loading) return <div>로딩중..</div>;
  if (error) return <div>에러가 발생했습니다</div>;
  if (!users) return null;
  return (
    <ul>
      {users.map(user => (
        <li key={user.id}>
          {user.username} ({user.name})
        </li>
      ))}
    </ul>
  );
}

```