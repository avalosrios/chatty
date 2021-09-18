/* tslint:disable */
/* eslint-disable */
// @generated
// This file was automatically generated and should not be edited.

import { AddMessageData } from "./../../../types/globalTypes";

// ====================================================
// GraphQL mutation operation: AddMessage
// ====================================================

export interface AddMessage_addMessage {
  __typename: "Message";
  id: string;
  text: string;
}

export interface AddMessage {
  addMessage: AddMessage_addMessage | null;
}

export interface AddMessageVariables {
  messageData: AddMessageData;
}
