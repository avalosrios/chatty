import {useMutation} from '@apollo/client';
import React, {useState} from 'react';
import {Box, Button, Form, FormField, TextInput} from 'grommet';
import {SetUser} from '../queries/types/SetUser';
import {SET_USER} from '../queries/user.queries';
import {currentUser} from '../shared/apollo-client';

const defaultValue  = {
  name: '',
};

export const UserForm = () => {
  const [setUser, { loading, error}] = useMutation<SetUser>(SET_USER);
  const [value, setValue] = useState(defaultValue);

  if (loading) return (<div>'Loading ...'</div>);
  if (error) return (<div>`Error! ${error.message}`</div>);

  return(
    <Box width={'medium'}>
      <Form
        value={value}
        onChange={(nextValue, {touched}) => {
          setValue(nextValue);
        }}
        onSubmit={(event) => {
          const userData = event.value;
          setUser({
            variables: { userData },
            update(cache, result) {
              // Use apollo client to update the local state
              currentUser(result.data?.setUser?.id);
            },
          });
          setValue(defaultValue);
        }}
      >
        <FormField label={'Name'} name={'name'}>
          <TextInput name={'name'} required/>
        </FormField>
        <Box direction="row" justify={'center'} margin={{ top: 'medium' }}>
          <Button type={'submit'} label={'Go!'} primary/>
        </Box>
      </Form>
    </Box>
  );
};
