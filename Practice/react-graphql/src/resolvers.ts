import posts from "./posts";
import users from "./users";

import {
  CreatePostArgs,
  Post,
  PostQueryResolverArgs,
  UpdatePostArgs,
} from "./type";

const resolvers = {
  Query: {
    post: (_: any, args: PostQueryResolverArgs) => {
      // post 쿼리 resolver를 작성합니다.
      return posts.find((post) => post.id === args.id);
    },
    posts: () => {
      // posts 쿼리 resolver를 작성합니다.
      return posts;
    },
  },
  Mutation: {
    createPost: (_: any, args: CreatePostArgs) => {
      // createPost mutation resolver를 작성합니다.
      const post = {
          id: `post-${posts.length + 1}`,
          title: args.data.title,
          content: args.data.content,
          writerId: args.data.writerId
      };
      posts.push(post);
      return post;
    },
    updatePost: (_: any, args: UpdatePostArgs) => {
      // updatePost mutation resolver를 작성합니다.
      const post = posts.find((post) => post.id === args.id);
      if (!post) {
          throw new Error('대상 포스트가 존재하지 않습니다.');
      }
      post.content = args.data.content;
      return post;
    },
  },
  Post: {
    writer: (post: Post) => {
      // writer schema resolver를 작성합니다.
      return users.find((user) => user.id === post.writerId);
    },
  },
};

export default resolvers;