import {MockedProvider} from '@apollo/client/testing';
import {render} from '@testing-library/react';
import React from 'react';
import {Message} from './Message';

describe('Message', () => {
  it('renders', () => {
    render(
      <MockedProvider mocks={[]} addTypename={false}>
        <Message/>
      </MockedProvider>
    )
  });
});
