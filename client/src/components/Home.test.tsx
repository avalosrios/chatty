import {MockedProvider} from '@apollo/client/testing';
import {render, screen} from '@testing-library/react';
import React from 'react';
import {Home} from './Home';

const mocks: any = [];
describe('Home component', () => {
  it('renders', () => {
    render(
      <MockedProvider mocks={mocks} addTypename={false}>
        <Home/>
      </MockedProvider>
    );
    const heading = screen.getByText(/Chatty/i);
    expect(heading).toBeInTheDocument();
  });
});
