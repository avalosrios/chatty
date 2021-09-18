import React from 'react';
import {Box} from 'grommet';

export const AppBar = (props: any) => {
  return (
    <Box
      tag='header'
      direction='row'
      align='center'
      justify='between'
      pad={{ left: 'medium', right: 'small', vertical: 'small' }}
      elevation='medium'
      {...props}
    />
  );
};
