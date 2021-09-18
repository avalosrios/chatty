import {useMutation, useQuery} from '@apollo/client';
import {Box, Button, Form, FormField, TextArea} from 'grommet';
import React, {useState} from 'react';
import {ADD_MESSAGE} from '../queries/message.queries';
import {AddMessage} from '../queries/types/AddMessage';
import {CurrentUser} from '../queries/types/CurrentUser';
import {CURRENT_USER} from '../queries/user.queries';

export const SendMessageForm = () => {
  const { data: userQuery } = useQuery<CurrentUser>(CURRENT_USER);
  const [message, setMessage] = useState('');
  const [addMessage, { error: messageError }] = useMutation<AddMessage>(ADD_MESSAGE);

  if (messageError) return (<div>`Error! ${messageError.message}`</div>);

  return (
    <>
      <Form
        onSubmit={event =>{
          const messageData = {
            text: message,
            userID: userQuery?.currentUser,
          };
          addMessage({
            variables: { messageData },
          });
          setMessage('');
        }}
      >
        <Box direction="row" justify="between" margin={{ top: 'medium' }}>
          <Box border={{ color: 'brand', size: 'medium' }}>
            <FormField labe={'Message'} name={'text'} required>
              <TextArea
                name={'text'}
                value={message}
                onChange={event => setMessage(event.target.value)}
                fill
                plain
                resize={false}
              />
            </FormField>
          </Box>
          <Button type="submit" label="Send" primary/>
        </Box>
      </Form>
    </>
  );
};
