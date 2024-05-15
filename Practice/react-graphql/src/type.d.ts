export interface Post {
    id: string;
    title: string;
    content: string;
    writerId: string;
  }
  
export interface User {
  id: string;
  firstName: string;
  lastName: string;
}

export interface CreatePostInput {
  title: string;
  content: string;
  writerId: string;
}

export interface UpdatePostInput {
  content: string;
}

export interface PostQueryResolverArgs {
  id: string;
}

export interface CreatePostArgs {
  data: CreatePostInput;
}

export interface UpdatePostArgs {
  id: string;
  data: UpdatePostInput;
}
