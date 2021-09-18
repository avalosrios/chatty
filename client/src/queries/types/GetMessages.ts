/* tslint:disable */
/* eslint-disable */
// @generated
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL query operation: GetMessages
// ====================================================

export interface GetMessages_messages_edges_node_user {
  __typename: "User";
  id: string;
  name: string;
}

export interface GetMessages_messages_edges_node {
  __typename: "Message";
  id: string;
  text: string;
  createdAt: string;
  user: GetMessages_messages_edges_node_user | null;
}

export interface GetMessages_messages_edges {
  __typename: "MessageEdge";
  cursor: string | null;
  node: GetMessages_messages_edges_node | null;
}

export interface GetMessages_messages {
  __typename: "MessageConnection";
  edges: (GetMessages_messages_edges | null)[] | null;
}

export interface GetMessages {
  messages: GetMessages_messages | null;
}
