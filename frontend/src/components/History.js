import React from 'react';
import { connect } from 'react-redux';
import { Box } from 'adminlte-2-react';
import Chart from "react-apexcharts";
import { defaultUpperBound } from '../callableRESTs';
import ReactPaginate from 'react-paginate';


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({});

const History = (props) => {
  return (
    <Box title="Storico" type="primary" collapsable footer={"Ultimo aggiornamento "+props.apex.lastUpdate}>
      <div className="mixed-chart">
        <Chart
          options={props.apex.options}
          series={props.apex.series}
          type="area"
          width="100%"
          height="183"
        />
      </div>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(History);