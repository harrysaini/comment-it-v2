import React from 'react';
import {map} from 'lodash';
import SingleComment from './SingleComment';
import { reverseMap } from '../../../utils/reverseMap';

interface Props {
  replies: any[];
  showReplyInput: boolean;
  setComments: (val: any, cb: any) => void;
}

const Replies: React.FunctionComponent<Props> = (props: Props) => {

  const replies = props.replies;

  const repliesJSX = reverseMap(replies, (reply, index) => {
    return <SingleComment comment={reply} key={index} setComments={props.setComments}/>
  })

  return (
    <div>
      {repliesJSX}
    </div>
  );

}

export default Replies;
