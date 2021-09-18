/* tslint:disable */
/* eslint-disable */
// @generated
// This file was automatically generated and should not be edited.

import { SetUserData } from "./../../../types/globalTypes";

// ====================================================
// GraphQL mutation operation: SetUser
// ====================================================

export interface SetUser_setUser {
  __typename: "User";
  id: string;
  name: string;
}

export interface SetUser {
  setUser: SetUser_setUser | null;
}

export interface SetUserVariables {
  userData: SetUserData;
}
