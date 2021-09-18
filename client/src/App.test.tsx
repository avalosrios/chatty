import {MockedProvider} from '@apollo/client/testing';
import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  const mocks: any = [];
  render(
    <MockedProvider mocks={mocks} addTypename={false}>
      <App/>
    </MockedProvider>
  );
});
