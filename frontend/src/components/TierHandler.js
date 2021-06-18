import React, { useState } from 'react'
import { Box } from 'adminlte-2-react';
import PopUp from './PopUp';
import { connect } from 'react-redux';
import { getErrorToast, getLoadingToast, stopLoadingToast } from '../toastManager';
import { _addTier, _getClientOfTier, _removeTier, _updateTier } from '../callableRESTs';
import Dropdown from 'react-dropdown';
import { addTier, removeTier, updateTier } from '../ActionCreator';
import Collapsible from 'react-collapsible';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ClientOfTier } from "./ClientOfTier";

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
 const mapDispatchToProps = dispatch => ({
    AddTier: (tier) => {
        dispatch(addTier(tier))
    },
    RemoveTier: (tierToRemove) => {
        dispatch(removeTier(tierToRemove))
    },
    UpdateTier: (tierToChange,ClientsToRemove) => {
        dispatch(updateTier(tierToChange, ClientsToRemove))
    },
 });
    
  /**
   * connect the redux state to the component
   * @param {*} state 
   */
  const mapStateToProps = state => ({
      categories_list: state.categories_list,
      token: state.token,
      id_company: state.id_company
  });

const TierHandler = (props) => {

    const [state,setState] = useState({
        tier: "",
        tierToRemove: "",
        tierToChange: "",
        clientsToMove: [],
        changeState: true
    })

  const openToggle = (id) => {
      var elem = document.getElementsByClassName("arrowAccordion"+id)[0];
      elem.style.transform = "rotate(90deg)";
      elem.style.transition = "transform 1s ease";
      elem.style.display = "inline-block";
  }
  
  const closeToggle = (id) => {
      var elem = document.getElementsByClassName("arrowAccordion"+id)[0];
      elem.style.transform = "rotate(0deg)";
      elem.style.transition = "transform 1s ease";
      elem.style.display = "inline-block";
  }
    
  const handleAddTier = (evt) => {
    setState((previousState) => {
      return { ...previousState, tier: evt.target.value };
    });
  }

  const addTierChilds = () => {
   return (
      [
      <>
        <div className="col-md-12 col-xs-12">
          <form>
            <div className="form-group">
              <label htmlFor="LoginEmail1">Inserisci il nome del tier:</label>
              <input type="email" value={state.tier} className="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder={"Server Tier 1"} onChange={handleAddTier}/>
            </div>
            
          </form>
          <center><button className="btn btn-primary" onClick={() => addTierClick()}>Aggiungi</button></center>
        </div>
      </>
      ]
   )
  }

  const addTierClick = () => {
    const loadingToast = getLoadingToast("Aggiungendo il tier...");
    _addTier(props.token, props.id_company, state.tier)
    .then( response => {
      props.AddTier(state.tier)
      stopLoadingToast(loadingToast)
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const handleRemoveTier = (evt) => {
    setState((previousState) => {
      return { ...previousState, tierToRemove: evt.value };
    });
  }

  const removeTierChilds = () => {

    const options = [];
    props.categories_list.forEach( category => {
      options.push(category.nome_categoria)
    })

    return (
       [
       <>
         <div className="col-md-12 col-xs-12">
           <form>
             <div className="form-group">
               <label htmlFor="LoginEmail1">Seleziona il tier da eliminare</label> 
               <Dropdown options={options} placeholder="Seleziona tier" onChange={handleRemoveTier}/>
             </div>
           </form>
           <center><button className="btn btn-primary" onClick={removeTierClick}>Rimuovi</button></center>
         </div>
       </>
       ]
    )
  }

  const removeTierClick = () => {
    const loadingToast = getLoadingToast("Rimuovendo il tier...");
    _removeTier(props.token, props.id_company, state.tierToRemove)
    .then( response => {
      props.RemoveTier(state.tierToRemove)
      stopLoadingToast(loadingToast)
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const changeTier = () => {
    const loadingToast = getLoadingToast("Spostando i clients...");
    if(state.tierToChange !== "" && state.clientsToMove.length !== 0) {
      _updateTier(props.token,state.clientsToMove, state.tierToChange)
        .then( response => {
          props.UpdateTier(state.tierToChange, state.clientsToMove);
          setState((state) => ({...state, clientsToMove:[], changeState: !state.changeState}))
          stopLoadingToast(loadingToast)
        })
        .catch(function (error) {
          stopLoadingToast(loadingToast);
          getErrorToast(String(error));
        });
    } else {
      stopLoadingToast(loadingToast);
      (state.tierToChange === "") ? getErrorToast(String("Selezionare il tier di destinazione")) : getErrorToast(String("Selezionare almeno un client"));
    }
  }

  const changeTierChilds = () => {
    const options = [];
    props.categories_list.forEach( category => {
      options.push(category.nome_categoria)
    })

    
    let childs = [];

    childs = props.categories_list.map( (category,i) => {
      return (
        <Collapsible key={i} onOpen={()=>openToggle(category.nome_categoria)} onClose={()=>closeToggle(category.nome_categoria)} trigger={<div className="clickable"><h4><span className={"arrowAccordion" + category.nome_categoria}><FontAwesomeIcon icon={["fas", "chevron-right"]} /></span> {category.nome_categoria} </h4></div>}>      
          <ClientOfTier category={category.nome_categoria} token={props.token} id_company={props.id_company} setState={setState} state={state} />
        </Collapsible>
      )
    })

    childs.push(
      <div>
        <Dropdown options={options} placeholder="Seleziona tier" onChange={(e) => setState({...state,tierToChange: e.value})}/>
        <button className="btn btn-primary" onClick={changeTier}>Sposta</button>
      </div>
    )

    return childs;
  }


    return (
        <Box title="Gestione dei Tier" type="primary" collapsable>
            <div className="btns-container">
                <PopUp title="Aggiungi Tier" messageLink={"Aggiungi Tier"} linkClass={"clickable"} childs={addTierChilds()} action={()=>(null)}/>
                <PopUp title="Rimuovi Tier" messageLink={"Rimuovi Tier"} linkClass={"clickable"} childs={removeTierChilds()} action={()=>(null)}/>
                <PopUp title="Sposta Client" messageLink={"Cambia Tier per il client"} linkClass={"clickable"} childs={changeTierChilds()} action={()=>(null)}/>
            </div>
        </Box>
    )
}

export default connect(
    mapStateToProps,
    mapDispatchToProps,
  )(TierHandler);