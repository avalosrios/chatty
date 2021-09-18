import {MockedProvider} from '@apollo/client/testing';
import React from 'react';
import { render, screen } from '@testing-library/react';
import {Chat} from './Chat';

const mocks: any = [];
describe('Chat component', () => {
  it('renders', () => {
    render(
      <MockedProvider mocks={mocks} addTypename={false}>
        <Chat/>
      </MockedProvider>
    );
    const loading = screen.getByText(/Loading../i);
    expect(loading).toBeInTheDocument();
  });
});
