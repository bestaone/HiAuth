import React from 'react';
import {Select, Tag} from "antd";
import {Option} from "antd/es/mentions";
import debounce from "lodash/debounce";
import styles from './index.less';
import {PlusOutlined} from "@ant-design/icons";

export type EditableTagValueType = {
  id: number;
  text: string;
};

type EditableTagProps = {
  value?: EditableTagValueType | null;
  onSearch: (value: string) => Promise<EditableTagValueType[]>;
  onSelect?: (id: number) => Promise<boolean>;
  onDelete?: (EditableTagValueType: EditableTagValueType) => Promise<boolean>;
};

type EditableTagState = {
  selectVisible: boolean;
  selectValue?: string;
  selectList?: EditableTagValueType[];
};

class EditableTag extends React.Component<EditableTagProps, EditableTagState> {

  private select: any;

  state = {
    value: this.props.value,
    selectVisible: false,
    selectValue: '',
    selectList: []
  };

  constructor(props: any) {
    super(props);
    console.log(props);
  }

  handleInputChange = (value: string) => {
    if(value && value.length>0){
      this.setState({ selectValue: value });
    }
  };

  handleSelectOnSearch = debounce((value: string) => {
    this.props.onSearch(value).then((tags: EditableTagValueType[]) => {
      this.setState({selectList: tags})
    });
  }, 1000);

  handleInputConfirm = () => {
    const { selectValue } = this.state;
    if(selectValue && selectValue.length>0){
      const { value } = this.props;
      if (value?.id!==Number(selectValue)) {
        const newVaule: EditableTagValueType = this.state.selectList.find((e: EditableTagValueType) => {
          return e.id === Number(selectValue);
        });
        this.setState({value: newVaule})
        this.props.onSelect?.(Number(selectValue));
      }
    }
    this.setState({
      selectVisible: false,
      selectValue: '',
    });
  };

  tagCloseHandle = (tag: EditableTagValueType) => {
    this.props.onDelete?.(tag).then(() => {
      this.setState({value: null})
    });
  }

  showSelect = () => {
    this.setState({ selectVisible: true }, () => this.select.focus());
  };

  saveSelectRef = (select: any) => {
    this.select = select;
  };

  render() {
    const {selectVisible, selectList, value} = this.state;
    return (
      <>
        {selectVisible && (
          <Select
            showSearch={true}
            showArrow={false}
            filterOption={false}
            placeholder={'请选择角色'}
            className={styles.tagSelect}
            ref={this.saveSelectRef}
            onChange={this.handleInputChange}
            onBlur={this.handleInputConfirm}
            onSelect={this.handleInputChange}
            onSearch={this.handleSelectOnSearch}
          >
            {selectList.map( (tag: EditableTagValueType) =>
              (<Option key={`${tag.id}`} value={`${tag.id}`}>{tag.text}</Option>)
            )}
          </Select>
        )}
        {!selectVisible && (

          value ? (
            <Tag className={styles.editTag} key={value.id} closable={true} onClick={this.showSelect} onClose={()=>{ this.tagCloseHandle(value); }}>
              <span>{value.text}</span>
            </Tag>
          ) : (
            <Tag className={styles.editTag} closable={false} onClick={this.showSelect}>
              <PlusOutlined />
            </Tag>
          )

        )}
      </>
    );
  }
}

export default EditableTag;
