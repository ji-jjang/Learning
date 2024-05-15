import { ApolloServer } from "@apollo/server";
import { startStandaloneServer } from "@apollo/server/standalone";
import typeDefs from "./typeDefs";
import resolvers from "./resolvers";

const server = new ApolloServer({
  typeDefs,
  resolvers,
});

startStandaloneServer(server, {
  listen: { port: 8080 },
}).then(({ url }: { url: string }) => {
  console.log(`🚀  Server ready at: ${url}`);
});

export { typeDefs, resolvers };