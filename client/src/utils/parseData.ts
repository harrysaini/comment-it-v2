
const isDateToday = (d: Date) => {
  const dTimeStand = d.getTime();
  const now = (new Date()).getTime();

  const diff = (now - dTimeStand)/(1000*60*60*24);

  return (diff < 1);
}

export const parseDate = (date: string) => {
  const d = new Date(date);
  if(isDateToday(d)) {
    return d.toLocaleTimeString();
  } else {
    return d.toLocaleDateString();
  }
}
