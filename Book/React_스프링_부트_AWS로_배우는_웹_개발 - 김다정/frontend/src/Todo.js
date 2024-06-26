import React from "react";
import { ListItem, ListItemText, InputBase, Checkbox, ListItemSecondaryAction, IconButton} from "@mui/material"
import { DeleteOutline } from "@mui/icons-material";

class Todo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { item: props.item, readOnly: true };
        this.delete = props.delete;
        this.update = props.update;
    }

    deleteEventHandler = () => {
        this.delete(this.state.item);
    };

    offReadOnlyMode = () => {
        console.log("Event!", this.state.readOnly)
        this.setState({ readOnly: false}, () => {
            console.log("ReadOnly? ", this.state.readOnly)
        });
    }

    enterKeyEventHandler = (e) => {
        if (e.key === "Enter") {
            this.setState({ readOnly: true});
            this.update(this.state.item);
        }
    }

    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
    }

    checkboxEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({ item: thisItem });
        this.update(this.state.item);
    }

    render() {
        const item = this.state.item;
        return (
            <ListItem>
                <Checkbox checked={item.done} onChange={this.checkboxEventHandler} />
                <ListItemText>
                    <InputBase
                    inputProps={{
                         "aria-label": "naked",
                         readOnly: this.state.readOnly,
                    }}
                    type="text"
                    id={`item.id`}
                    name={`item.id`}
                    value={item.title}
                    fullWidth={true}
                    onClick={this.offReadOnlyMode}
                    onChange={this.editEventHandler}
                    onKeyDown={this.enterKeyEventHandler}
                    />
                </ListItemText>

                <ListItemSecondaryAction>
                    <IconButton 
                        aria-label="Delete Todo"
                        onClick={this.deleteEventHandler}>
                        <DeleteOutline />
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        );
    }
}

            // <div className="Todo">
            //     <input type="checkbox" 
            //     id={this.state.item.id}
            //     name={this.state.item.id}
            //     checked={this.state.item.done}
            // />
            // <label id={this.state.item.id}> {this.state.item.title}</label>
            // </div>
export default Todo;