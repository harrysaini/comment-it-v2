import React, { BaseSyntheticEvent } from 'react';
import './Comment.css';
import CommentService from '../../services/comment.service';
import { map } from 'lodash';
import { AuthService } from '../../services/auth.service';
import InputBox from './components/InputBox';
import SingleComment from './components/SingleComment';
import LoadingSpinner from '../../_shared-components/LoadingSpinner';
import { reverseMap } from '../../utils/reverseMap';

interface State {
  comments: any[],
  isLoaded: boolean;
}

interface Props {
}

class Comments extends React.Component<Props, State> {

  constructor(props: Props) {
    super(props);
    this.state = {
      comments: [],
      isLoaded: false
    }

  }

  componentDidMount = async () => {
    try {
      const comments = await CommentService.getComments();
      this.setState({
        comments,
        isLoaded: true
      });
    } catch(e) {
      alert(e.message);
    }
  }

  postComment = async (commentString: string) => {
    try{
      const comment = await CommentService.postComment({
        text: commentString,
        userId: AuthService.user.id
      });

      this.setState({
        comments: this.state.comments.concat([comment]),
      });

    } catch(e) {
      alert(e);
      throw e;
    }
  }

  setComments = (comment: any, cb: any) => {
    cb(comment);
    this.forceUpdate();
  }

  render() {

    if(!this.state.isLoaded) {
      return <LoadingSpinner />;
    }

    const comments = reverseMap(this.state.comments, (comment, index) => {
      return <SingleComment comment={comment} key={index} setComments={this.setComments}/>
    })

    return (
      <div>
        <div>
          <InputBox
            rows={3}
            task={this.postComment}
          />
        </div>
        <div className='comments'>
          {comments}
        </div>
      </div>
    );
  }

}

export default Comments;
