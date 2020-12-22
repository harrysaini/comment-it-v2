import { config } from '../config';
import { each } from 'lodash';
import { AuthService } from './auth.service';

const getCommentsUrl = config.apiUrl + '/api/v1/comment';
const postCommentUrl = config.apiUrl + '/api/v1/comment';

const headers = {
  ...AuthService.getAuthHeaders(),
  "Content-type": "application/json"
}

interface ICommentPostRequest {
  text: string;
  userId: number;
  parentCommentId?: number;
}

class CommentService {

  static async getComments(): Promise<any[]> {
    try {
      const resp = await fetch(getCommentsUrl, {
        method: 'get',
        headers: AuthService.getAuthHeaders()
      });

      const response = await resp.json();
      if (response.status.code !== 0) {
        throw new Error(response.status.message);
      }

      const comments =  response.data;
      return comments;
    } catch (e) {
      throw e;
    }
  }

  static async postComment(obj: ICommentPostRequest): Promise<any> {
    try {
      const resp = await fetch(postCommentUrl, {
        method: 'post',
        headers: headers,
        body: JSON.stringify(obj)
      });

      const response = await resp.json();
      if (response.status.code !== 0) {
        throw new Error(response.status.message);
      }

      const comment =  response.data;
      comment.user = AuthService.user;
      comment.replies = [];
      return comment;
    } catch (e) {
      throw e;
    }
  }

  static async editComment(id: number, text: string): Promise<any> {
    try {
      const resp = await fetch(`${postCommentUrl}/${id}`, {
        method: 'put',
        headers: headers,
        body: JSON.stringify({
          text
        })
      });

      const response = await resp.json();
      if (response.status.code !== 0) {
        throw new Error(response.status.message);
      }

      const comment =  response.data;
      return comment;
    } catch (e) {
      throw e;
    }
  }

}

export default CommentService;
