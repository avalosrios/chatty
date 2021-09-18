import {MockedProvider} from '@apollo/client/testing';
import {act, render, screen} from '@testing-library/react';
import React from 'react';
import {CURRENT_USER} from '../queries/user.queries';
import {Home} from './Home';

describe('Home component', () => {
  it('renders', () => {
    const currentUserMock = {
      request: {
        query: CURRENT_USER,
      },
      result: {
        data: { currentUser: 'test' },
      },
    };
    act( () => {
      render(
        <MockedProvider mocks={[currentUserMock]} addTypename={false}>
          <Home/>
        </MockedProvider>
      )
    });
    const heading = screen.getByText(/Chatty/i);
    expect(heading).toBeInTheDocument();
  });
});
