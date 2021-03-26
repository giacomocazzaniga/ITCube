import React from 'react';
import { connect } from 'react-redux';
import { categoryVsPlace } from '../ActionCreator';


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
    Toggle: (actual_state) => {
      dispatch(categoryVsPlace(!actual_state))
    }
  }
);

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    category_vs_place: state.category_vs_place
  }
);

const ToggleCategoryPlace = (props) => {
  const onChangeValue = event => {
    props.Toggle(props.category_vs_place);
  }
  return (
    <>
      <span onChange={onChangeValue}>
        {props.category_vs_place
        ? <>
            <br /><input type="radio" value="category" name="toggleView" checked="checked"/> Tipologia dispositivi
            <br /><input type="radio" value="place" name="toggleView" /> Categoria
          </>
        :
          <>
            <br /><input type="radio" value="category" name="toggleView"/> Tipologia dispositivi
            <br /><input type="radio" value="place" name="toggleView" checked="checked"/> Categoria
          </>}
        
      </span>
  </>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(ToggleCategoryPlace);