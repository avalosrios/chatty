import {gql} from '@apollo/client';

export const CORE_MESSAGE_FIELDS = gql`
    fragment CoreMessageFields on Message {
        id
        text
        createdAt
        user {
            id
            name
        }
    }
`;

export const GET_MESSAGES = gql`
    ${CORE_MESSAGE_FIELDS}
    query GetMessages {
        messages {
            edges {
                cursor
                node {
                    ...CoreMessageFields
                }
            }
        }
    }
`;

export const ADD_MESSAGE = gql`
    mutation AddMessage($messageData: AddMessageData!) {
        addMessage(input: $messageData) {
            id
            text
        }
    }
`;

export const MESSAGES_SUBSCRIPTION = gql`
    ${CORE_MESSAGE_FIELDS}
    subscription MessageAdded {
        messageAdded {
            ...CoreMessageFields
        }
    }
`;
