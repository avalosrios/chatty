import {useQuery} from '@apollo/client';
import {Box, Heading} from 'grommet';
import React from 'react';
import {CurrentUser} from '../queries/types/CurrentUser';
import {CURRENT_USER} from '../queries/user.queries';
import {AppBar} from './AppBar';
import {Chat} from './Chat';
import {UserForm} from './UserForm';

export const Home = () => {
  const { data } = useQuery<CurrentUser>(CURRENT_USER);
  const renderContent = () => {
    return data?.currentUser !== '' ? <Chat/> : <UserForm/>;
  };

  return(
    <>
      <AppBar>
        <Heading level={3} margin={'none'}>
          Chatty
        </Heading>
      </AppBar>
      <Box fill align={'center'} justify={'center'}>
        { renderContent() }
      </Box>
    </>
  )
};
