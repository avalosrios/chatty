import {Box, Spinner, Text} from 'grommet';
import React from 'react';

export const Loading = () => {
  return(
    <Box align="center" direction="row" gap="small">
    <Spinner
      border={[
        {
          side: 'all',
          color: 'brand',
          size: 'medium',
          style: 'dotted',
        },
      ]}
    />
    <Text>Loading...</Text>
  </Box>)
};
