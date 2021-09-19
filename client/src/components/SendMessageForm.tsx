import {useMutation} from '@apollo/client';
import {Box, Button, Form, FormField, TextArea} from 'grommet';
import React, {useState} from 'react';
import {ADD_MESSAGE} from '../queries/message.queries';
import {AddMessage} from '../queries/types/AddMessage';

interface SendMessageFormProps {
  userID: string;
}

export const SendMessageForm = ({ userID }: SendMessageFormProps) => {
  const [message, setMessage] = useState('');
  const [addMessage, { error: messageError }] = useMutation<AddMessage>(ADD_MESSAGE);

  if (messageError) return (<div>`Error! ${messageError.message}`</div>);

  return (
    <Box>
      <Form
        onSubmit={event =>{
          const messageData = {
            text: message,
            userID,
          };
          addMessage({
            variables: { messageData },
          });
          setMessage('');
        }}
      >
        <Box direction="row" justify="between" margin={{ top: 'medium' }} gap={'small'}>
          <Box border={{ color: 'neutral-3', size: 'small' }} round={'xsmall'}>
            <FormField labe={'Message'} name={'text'} required>
              <TextArea
                name={'text'}
                value={message}
                onChange={event => setMessage(event.target.value)}
                fill
                plain
                resize={false}
                focusIndicator={false}
              />
            </FormField>
          </Box>
          <Box direction={'row'}>
            <Button type={'submit'} label={'Send'} primary fill color={'neutral-3'}/>
          </Box>
        </Box>
      </Form>
    </Box>
  );
};
