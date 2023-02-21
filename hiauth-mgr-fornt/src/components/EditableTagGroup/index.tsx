import React from 'react';
import {Select, Tag, Tooltip} from "antd";
import {Option} from "antd/es/mentions";
import {PlusOutlined} from "@ant-design/icons";
import debounce from "lodash/debounce";
import styles from './index.less';

export type TagType = {
  id: number;
  text: string;
  closeable: boolean;
};

type MyProps = {
  tags: TagType[];
  onSearch: (value: string) => Promise<TagType[]>;
  onClose: (id: number) => Promise<boolean>;
  onAdd: (id: number) => Promise<boolean>;
};

type MyState = {
  selectVisible: boolean;
  selectValue?: string;
  selectList?: TagType[];
};

class EditableTagGroup extends React.Component<MyProps, MyState> {

  private select: any;

  state = {
    selectVisible: false,
    selectValue: '',
    selectList: []
  };

  constructor(props: any) {
    super(props);
    console.log(props);
  }

  handleTagClose = (tag: TagType) => {
    this.props.onClose(tag.id).then(() => {
      this.props.tags.splice(this.props.tags.findIndex(item => item.id === tag.id), 1);
    });
  };

  handleInputChange = (value: string) => {
    if(value && value.length>0){
      this.setState({ selectValue: value });
    }
  };

  handleSelectOnSearch = debounce((value: string) => {
    this.props.onSearch(value).then((tags: TagType[]) => {
      this.setState({selectList: tags})
    });
  }, 1000);

  handleInputConfirm = () => {
    const { selectValue } = this.state;
    if(selectValue && selectValue.length>0){
      const { tags } = this.props;
      if (selectValue && !tags.find( (t: TagType) => t.id===Number(selectValue) )) {
        const newTag: TagType = this.state.selectList.find((e: TagType) => {
          return e.id === Number(selectValue);
        });
        tags.push(newTag);
        this.props.onAdd(Number(selectValue));
      }
    }
    this.setState({
      selectVisible: false,
      selectValue: '',
    });
  };

  showSelect = () => {
    this.setState({ selectVisible: true }, () => this.select.focus());
  };

  saveSelectRef = (select: any) => {
    this.select = select;
  };

  render() {

    const {tags} = this.props;
    const {selectVisible, selectList} = this.state;
    return (
      <>

        {tags.map((tag: TagType) => {
          const isLongTag = tag.text.length > 10;
          const tagElem = (
            <Tag className={styles.editTag} key={tag.id} closable={true} onClose={()=>{ this.handleTagClose(tag); }}>
              <span>{isLongTag ? `${tag.text.slice(0, 10)}...` : tag.text}</span>
            </Tag>
          );
          return isLongTag ? (<Tooltip title={tag.text} key={tag.id}> {tagElem} </Tooltip>) : (tagElem);
        })}

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
            {selectList.map( (tag: TagType) =>
              (<Option key={`${tag.id}`} value={`${tag.id}`}>{tag.text}</Option>)
            )}
          </Select>
        )}

        {!selectVisible && (
          <Tag className={styles.siteTagPlus} onClick={this.showSelect}>
            <PlusOutlined />
          </Tag>
        )}

      </>
    );
  }

}

export default EditableTagGroup;
