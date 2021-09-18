import {
    ApolloClient, createHttpLink,
    InMemoryCache,
    NormalizedCacheObject,
} from '@apollo/client';

const httpLink = createHttpLink({uri:'http://localhost:8080/graphql' })

const apolloClient: ApolloClient<NormalizedCacheObject> = new ApolloClient({
    link: httpLink,
    cache: new InMemoryCache(),
    headers: {},
    resolvers: {},
});

export const client = apolloClient;
