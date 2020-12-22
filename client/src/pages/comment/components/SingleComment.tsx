import React, { BaseSyntheticEvent } from 'react';
import Replies from './Replies';
import InputBox from './InputBox';
import CommentService from '../../../services/comment.service';
import { AuthService } from '../../../services/auth.service';
import { config } from '../../../config';
import {parseDate} from '../../../utils/parseData';

const STOP_RECURSION_LEVEL = config.STOP_RECURSION_LEVEL;

interface Props {
  comment: any;
  setComments: (obj: any, cb: any) => void
}
interface State {
  isEditable: boolean;
  editButtonText: string;
  showReplies: boolean;
  showReplyInput: boolean;
}

class SingleComment extends React.Component<Props, State> {

  constructor(props: Props) {
    super(props);
    this.state = {
      isEditable: false,
      editButtonText: 'Edit',
      showReplies: false,
      showReplyInput: false
    }
  }



  editComment = async (val: string) => {
    try{
      const updatedComment = await CommentService.editComment(this.props.comment.id, val);



      this.props.setComments(this.props.comment, (comment: any) => {
        comment.text = updatedComment.text;
      });

    } catch(e) {
      alert(e);
      throw e;
    }
  }

  onEditDone = () => {
    this.setState({
      isEditable: false,
      editButtonText: 'Edit'
    });
  }

  replyComment = async (val: string) => {
    try{
      const reply = await CommentService.postComment({
        text: val,
        userId: AuthService.user.id,
        parentCommentId: this.props.comment.id
      });


      this.props.setComments(this.props.comment, (comment: any) => {
        comment.replies.push(reply);
      });

      return;

    } catch(e) {
      alert(e);
      throw e;
    }
  }

  onReplyDone = ():void =>  {
    this.setState({
      showReplyInput: false
    });
  }

  onEditCommentClick = () => {
    if(!this.state.isEditable) {
      this.setState({
        isEditable: true,
        editButtonText: 'Cancel'
      });
    } else {
      this.setState({
        isEditable: false,
        editButtonText: 'Edit'
      });
    }

  }

  render() {

    const comment = this.props.comment;

    return (
      <div className="comment">
        <div className="d-flex ">
          <div>
            <img
              className="img-fluid user-image"
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQk4Lw8zSVdKxa7mRd_hZfY8mx1-ieO6P9m4nHMq63b77e2iecU"
            />
          </div>
          <div className="comment-data">
            <div className="comment-info">
              <div className="user-name">
                {comment.user.username}
              </div>
              <div className="date">
                {parseDate(comment.createdAt)}
              </div>
            </div>

            <div className="comment-text">
              {
                !this.state.isEditable ?
                (
                  <div>{this.props.comment.text}</div>
                ) : (
                  <InputBox
                    rows={1}
                    value={this.props.comment.text}
                    task={this.editComment}
                    onTaskDone={this.onEditDone}
                  />
                )
              }
            </div>

            <div className="comment-actions">
              { (AuthService.user.id === comment.user.id) &&
                <div
                  className="edit link-btn"
                  onClick={this.onEditCommentClick}>
                  {this.state.editButtonText}
                </div>
              }
              { comment.level < STOP_RECURSION_LEVEL &&
                <div
                  className="reply link-btn"
                  onClick={() => {
                    this.setState({
                      showReplies: true,
                      showReplyInput: !this.state.showReplyInput
                    })
                  }}
                >
                  Reply
                </div>
              }
              { comment.level < STOP_RECURSION_LEVEL && comment.replies && comment.replies.length > 0 &&
              (
                <div
                  className="replies link-btn"
                  onClick={() => {
                    this.setState({
                      showReplies: !this.state.showReplies
                    })
                  }}>
                {this.state.showReplies ? 'Hide' : comment.replies.length} Replies
                </div>
              )
              }

            </div>
            <hr />
            { this.state.showReplies &&
              <div className="replies col-offset-1">
                { this.state.showReplyInput &&
                  (
                    <div className="p-2">
                      <InputBox
                        rows={1}
                        task={this.replyComment}
                        onTaskDone={this.onReplyDone}
                      />
                      <hr/>
                    </div>
                  )
                }
                <Replies
                  replies={comment.replies}
                  showReplyInput={this.state.showReplyInput}
                  setComments={this.props.setComments}
                />
              </div>
            }
          </div>
        </div>
      </div>

    );
  }

}

export default SingleComment;
