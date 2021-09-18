import {Box} from 'grommet';
import React, {useEffect, useRef} from 'react';
import {useQuery} from '@apollo/client';
import {GET_MESSAGES} from '../queries/message.queries';
import {CurrentUser} from '../queries/types/CurrentUser';
import {GetMessages} from '../queries/types/GetMessages';
import {CURRENT_USER} from '../queries/user.queries';
import {Message} from './Message';
import {SendMessageForm} from './SendMessageForm';

export const Chat = () => {
  const { loading, error, data } = useQuery<GetMessages>(GET_MESSAGES);
  const { data: userQuery } = useQuery<CurrentUser>(CURRENT_USER);
  const messagesEndRef = useRef<null | HTMLDivElement>(null);

  const scrollToBottom = () => {
    messagesEndRef?.current?.scrollIntoView({ behavior: 'smooth' });
  }

  useEffect(() => {
    scrollToBottom();
  }, [data]);

  if (loading) return (<div>'Loading ...'</div>);
  if (error) return (<div>`Error! ${error.message}`</div>);
  return(
    <Box
      direction={'column'}
      height={ {
        min: '100%'
      }}
      responsive
      justify={'between'}
      width={'medium'}
      fill={'vertical'}
    >
      <Box direction={'column'} overflow={'scroll'}>
        { data?.messages?.edges?.map( (edge) => (
          edge?.node && <Message
              message={{
                id: edge.node.id,
                text: edge.node.text,
                userName: edge.node.user?.name || '',
                userID: edge.node.user?.id || '',
                createdAt: edge.node.createdAt,
              }}
              userID={userQuery?.currentUser || ''}/>
        )) }
        <div ref={messagesEndRef} />
      </Box>
      <Box direction={'row'} justify={'center'}>
        <SendMessageForm userID={userQuery?.currentUser || ''}/>
      </Box>
    </Box>
  );
};
