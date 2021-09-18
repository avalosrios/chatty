import {gql} from '@apollo/client';

export const SET_USER = gql`
  mutation SetUser($userData: SetUserData!) {
      setUser(input: $userData) {
          id
          name
      }
  }
`;

export const CURRENT_USER = gql`
    query CurrentUser {
        currentUser @client
    }
`;
