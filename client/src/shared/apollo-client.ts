import {
    ApolloClient,
    createHttpLink, gql,
    InMemoryCache,
    makeVar,
    NormalizedCacheObject, split,
} from '@apollo/client';
import {WebSocketLink} from '@apollo/client/link/ws';
import {getMainDefinition} from '@apollo/client/utilities';

const wsLink = new WebSocketLink({
    uri: 'ws://localhost:8080/subscriptions',
    options: {
        reconnect: true,
    },
});
const httpLink = createHttpLink({uri:'http://localhost:8080/graphql' });

const splitLink = split(
  ({ query }) => {
      const definition = getMainDefinition(query);
      return(
        definition.kind === 'OperationDefinition' &&
        definition.operation === 'subscription'
      );
  },
  wsLink,
  httpLink,
);

export const currentUser = makeVar<string>('');

const typeDefs = gql`
    extend type Query {
        currentUser: String!
    }
`;

export const cache = new InMemoryCache({
    typePolicies: {
        Query: {
            fields: {
                currentUser: {
                    read() {
                        return currentUser();
                    }
                }
            }
        }
    }
});

const apolloClient: ApolloClient<NormalizedCacheObject> = new ApolloClient({
    link: splitLink,
    cache: cache,
    headers: {},
    typeDefs: typeDefs,
});

export const client = apolloClient;
