import {MockedProvider} from '@apollo/client/testing';
import {render} from '@testing-library/react';
import React from 'react';
import {Message} from './Message';

describe('Message', () => {
  it('renders', () => {
    const mockMessage = {
      id: 'test',
      text: 'test',
      createdAt: Date().toString(),
      userName: 'test',
      userID: 'user',
    };
    render(
      <MockedProvider mocks={[]} addTypename={false}>
        <Message message={mockMessage} userID={'test'}/>
      </MockedProvider>
    )
  });
});
