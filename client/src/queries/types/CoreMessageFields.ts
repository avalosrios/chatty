/* tslint:disable */
/* eslint-disable */
// @generated
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL fragment: CoreMessageFields
// ====================================================

export interface CoreMessageFields_user {
  __typename: "User";
  id: string;
  name: string;
}

export interface CoreMessageFields {
  __typename: "Message";
  id: string;
  text: string;
  createdAt: string;
  user: CoreMessageFields_user | null;
}
