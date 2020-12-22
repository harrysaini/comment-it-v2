import React, { BaseSyntheticEvent } from 'react';


interface Props {
  rows: number,
  value?: string;
  task: (val: string) => Promise<any>;
  onTaskDone?: () => void
}
interface State {
  inputValue: string;
  submitButtonDisabled: boolean;
}

class InputBox extends React.Component<Props, State>  {

  constructor(props: Props) {
    super(props);
    this.state = {
      inputValue: props.value || '',
      submitButtonDisabled: false
    };
  }

  handleInputChange = (event: BaseSyntheticEvent) => {
    this.setState({
      inputValue: event.target.value
    });
  }


  onSubmitInputClick = async () => {
    if(this.state.inputValue === '') {
      return;
    }
    this.setState({
      submitButtonDisabled: true
    });

    try{
      await this.props.task(this.state.inputValue);

      this.setState({
        inputValue: '',
        submitButtonDisabled: false
      });
      this.props.onTaskDone && this.props.onTaskDone();

    } catch(e) {
      this.setState({
        submitButtonDisabled: false
      });
    }
  }


  render() {
    return (
      <div className="comment-wrapper">
        <div className="row">
          <div className="col-8 col-md-10">
            <textarea
              className="form-control"
              rows={this.props.rows}
              placeholder="Write comment"
              onChange={this.handleInputChange}
              value={this.state.inputValue}
            ></textarea>
          </div>
          <div className="col-4 col-md-2 comment-btn-wrapper">
            <button
              className="btn btn-primary comment-btn"
              onClick={this.onSubmitInputClick}
              disabled={this.state.submitButtonDisabled}
            >Submit</button>
          </div>
        </div>
      </div>
    );
  }
}

export default InputBox;
