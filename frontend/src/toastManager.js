import toast, { Toaster } from 'react-hot-toast';

let options = {
  className: '',
  style: {
    "margin-top": "5px",
    "margin-right": "5px"
  },
}

export const toaster = <Toaster toastOptions={options} position="top-right" reverseOrder={false}/>

const randomID = () => {
  return Math.floor(Math.random() * 10000) + 1;
}

export const getSuccessToast = (message) => {
  toast.success(message, {id: randomID()})
}

export const getErrorToast = (message) => {
  toast.error(message, {id: randomID()})
}

export const getLoadingToast = (message) => {
  toast.loading(message, {id: randomID()})
}

export const stopLoadingToast = (toastToDismiss) => {
  toast.dismiss(toastToDismiss);
}