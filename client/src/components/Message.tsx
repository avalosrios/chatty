import {
  Box,
  Text
} from 'grommet';
import { DateTime } from 'luxon';
import React from 'react';

interface TextMessage {
  id: string;
  text: string;
  userName: string;
  userID: string;
  createdAt: string;
}

interface MessageProps {
  message: TextMessage,
  userID: string,
}

export const Message = ({ message, userID }: MessageProps) => {
  const isOwnMessage: Boolean = userID === message.userID;
  const displayName = isOwnMessage ? 'You' : message.userName;
  const customBg = isOwnMessage ? '#F1FFFA' : 'light-1';
  return(
    <Box
      key={message.id}
      background={customBg}
      round={'small'}
      pad="small"
      gap="small"
      align="start"
      elevation="small"
      margin={'xsmall'}
      height={{ min: '100px' }}
      responsive
      justify={'between'}
      flex={'grow'}
    >
      <Box
        direction={'row'}
        responsive
      >
        <Text size={'small'}>{displayName}</Text>
      </Box>
      <Box
        direction={'row'}
        responsive
      >
        <Text>{ message.text }</Text>
      </Box>
      <Box
        direction={'row'}
        responsive
      >
        <Text size={'small'}>
          {DateTime.fromISO(message.createdAt).toLocaleString(DateTime.DATETIME_MED)}
        </Text>
      </Box>
    </Box>
  );
};

