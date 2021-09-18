import {Box, Grommet} from 'grommet';
import React from 'react';
import './App.css';
import {Home} from './components/Home';

const App = () => {
  const theme = {
    global: {
      font: {
        family: 'Roboto',
        size: '18px',
        height: '20px',
      },
    },
  };
  return (
    <Grommet theme={theme} full>
      <Box fill>
        <Home/>
      </Box>
    </Grommet>
  );
}

export default App;
