import React from 'react';
import {useQuery} from '@apollo/client';
import {GET_MESSAGES} from '../queries/message.queries';
import {GetMessages} from '../queries/types/GetMessages';

export const Chat = () => {
  const { loading, error, data } = useQuery<GetMessages>(GET_MESSAGES);
  if (loading) return (<div>'Loading ...'</div>);
  if (error) return (<div>`Error! ${error.message}`</div>);

  return(
    <div>
      { data?.messages?.edges?.map( ({ node }: any) => (
        <div key={node.id}>{node.text}</div>
      )) }
    </div>
  );
};
