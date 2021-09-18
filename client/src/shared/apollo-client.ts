import {
    ApolloClient,
    createHttpLink, gql,
    InMemoryCache,
    makeVar,
    NormalizedCacheObject,
} from '@apollo/client';

const httpLink = createHttpLink({uri:'http://localhost:8080/graphql' })

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
    link: httpLink,
    cache: cache,
    headers: {},
    typeDefs: typeDefs,
});

export const client = apolloClient;
