import {MockedProvider} from '@apollo/client/testing';
import {render, screen} from '@testing-library/react';
import React from 'react';
import {CURRENT_USER} from '../queries/user.queries';
import {SendMessageForm} from './SendMessageForm';

describe('SendMessageForm', () => {
  it('renders', () => {
    const currentUserMock = {
      request: {
        query: CURRENT_USER,
      },
      result: {
        data: { currentUser: 'test' },
      },
    };
    render(
      <MockedProvider mocks={[currentUserMock]} addTypename={false}>
        <SendMessageForm/>
      </MockedProvider>
    );
    const send = screen.getByText(/Send/i);
    expect(send).toBeInTheDocument();
  });
});
