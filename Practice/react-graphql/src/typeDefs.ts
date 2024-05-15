const typeDefs = `#graphql
  # SDL을 작성합니다.
  type Query {
      post(id: ID!): Post!
      posts: [Post!]!
  }

  input CreatePostInput {
      title: String!
      content: String!
      writerId: ID!
  }

  input UpdatePostInput {
      content: String!
  }

  type Mutation {
      createPost(data: CreatePostInput!): Post!
      updatePost(id: ID!, data: UpdatePostInput!): Post!
  }

  type User {
      id: ID!
      firstName: String 
      lastName: String
  }

  type Post {
      id: ID!
      title: String!
      content: String
      writer: User
  }
`;

export default typeDefs;