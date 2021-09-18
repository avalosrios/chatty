import {gql} from '@apollo/client';

export const GET_MESSAGES = gql`
    query GetMessages {
        messages {
            edges {
                cursor
                node {
                    id
                    text
                    createdAt
                    user {
                        id
                        name
                    }
                }
            }
        }
    }
`;
