/* tslint:disable */
/* eslint-disable */
// @generated
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL subscription operation: MessageAdded
// ====================================================

export interface MessageAdded_messageAdded_user {
  __typename: "User";
  id: string;
  name: string;
}

export interface MessageAdded_messageAdded {
  __typename: "Message";
  id: string;
  text: string;
  createdAt: string;
  user: MessageAdded_messageAdded_user | null;
}

export interface MessageAdded {
  messageAdded: MessageAdded_messageAdded;
}
