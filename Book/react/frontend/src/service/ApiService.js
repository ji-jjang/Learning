import { API_BASE_URL } from "../app-config";


export function call(api, method, request) {
  let options = {
    headers: new Headers({
      "Content-Type": "application/json",
    }),
    url: API_BASE_URL + api,
    method: method,
  };
  if (request) {
    options.body = JSON.stringify(request);
  }
  return fetch(options.url, options)
  .then(response => {
    if (!response.ok) {
      if (response.status === 403) {
        // 403 에러가 발생하면, 즉시 로그인 페이지로 리다이렉션
        window.location.href = "/login";
        // 여기서 Promise.reject()를 호출하지 않고, 처리를 중단합니다.
        // 또는 필요에 따라 적절한 에러 메시지와 함께 Promise를 거부할 수 있습니다.
        return Promise.reject({status: 403, message: 'Forbidden'});
      }
      // 다른 HTTP 에러에 대한 처리
      // 이 경우 응답이 JSON 형식을 가질 것이라고 가정합니다.
      return response.json().then(json => Promise.reject(json));
    }
    // 응답이 정상적인 경우, JSON으로 파싱
    return response.json();
  })
  .catch(error => {
    // 네트워크 오류 또는 response.json() 파싱 중 발생한 오류 처리
    console.error('Fetch error:', error);
    // 필요한 추가적인 에러 처리를 여기서 수행합니다.
    return Promise.reject(error);
  });
}

export function signin(userDTO) {
  return call("/auth/signin", "POST", userDTO).then((response) => {
    if (response.token) {
      // token이 존재하는 경우 Todo 화면으로 리디렉트
      window.location.href = "/";
    }
  });
}